# NaiveBayes

 **Naive Bayes model for language identification using n-grams.**
 
 The task at hand is that of predicting the language of documents using Naive Bayes. What we have, is a training dataset on   which we create a model and use the model on the test/cross-validation set and check the accuracy of the model.To improve    and fine tune the model, changes to the hyper-parameters are made.
 
**Model:**

The model is created on the data which has a new documents on each line with the language of the document. This data is fed into the code and a Naive Bayes model is created on the same. The equations used for the same and implemented in the various methods in the code are as below in the reverse order for brevity

P(Language,Document) = P(Language) * P(Document|Language)

P(Document|Language) = P(Language_1|Document) * P(Language_2|Document) *.....*P(Language_n|Document)

P(Language_1|Word) = P(Word|Langugae_1) * P(Language_1)

Therefore,

P(Language_1|Document) = (Product of [ P(Word|Language_n] for all languages) ]* P(Language_n)

P(Word|Language) = (Count of words in language)/(Count of total words in language)

P(Language) = (Number of document in the language)/(Total number of documents)

Using the above equations, we define the model and run the test data on the model to predict the language to which the document belongs to.
