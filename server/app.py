from sklearn.naive_bayes import GaussianNB
import shorttext
import json
from flask import Flask, request
app = Flask(__name__)


subdict = shorttext.data.retrieve_jsondata_as_dict(
    './training data/categories data.json')
topicmodeler = shorttext.generators.LDAModeler()

topicmodeler.train(subdict, 4)

classifier = shorttext.classifiers.TopicVectorSkLearnClassifier(
    topicmodeler, GaussianNB())
classifier.train(subdict)

@app.route('/api/categorizeText/')
def categorizeText():
    text = request.args["text"]
    score = classifier.score(text)
    for x in score:
        if score[x] == 1.0:
            return x
    return text

@app.route('/api/retrain/')
def retrainData():
    text = request.args["text"]
    category = request.args["category"]

    subdict[category].append(text)
    topicmodeler.train(subdict, 4)
    classifier = shorttext.classifiers.TopicVectorSkLearnClassifier(
        topicmodeler, GaussianNB())
    classifier.train(subdict)
    
    return json.dumps(subdict)


if __name__ == '__main__':
    app.run()
