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
def hello_world():
    text = request.args["text"]
    score = classifier.score(text)
    score = json.dumps(score)
    return score

if __name__ == '__main__':
    app.run()
