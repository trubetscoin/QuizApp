# QuizApp

## A simple plain java quiz demo applitcation.
Uses three tables: `question`, `quiz`, `response` to simulate quiz.
Made with plain java.

### Features
* CRUD functionality
* Unit tests

### Notes
Use the `quiz.sql` dump to easily create corresponding sql schema. <br>
Change `DBConnection` to your configuration. <br>
After running tests the `Illegal operation on empty result set` error is fine, since the test deletes the object and then tries to find non-existing object. It is done to assure that object cannot be found after deletion.