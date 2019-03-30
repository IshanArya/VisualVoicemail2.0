import shorttext
from sklearn.naive_bayes import GaussianNB

subdict = shorttext.data.retrieve_jsondata_as_dict('./training data/categories data.json')
topicmodeler = shorttext.generators.LDAModeler()

topicmodeler.train(subdict, 2)

# print(topicmodeler.retrieve_topicvec())
# print(topicmodeler.retrieve_topicvec())

classifier = shorttext.classifiers.TopicVectorSkLearnClassifier(
    topicmodeler, GaussianNB())
classifier.train(subdict)

print(classifier.score("Hey can you please call me back as soon as possible?"))
