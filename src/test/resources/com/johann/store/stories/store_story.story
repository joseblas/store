Scenario: Not Correct
Given a store
When the element INVALID-TEXT is searched for
Then excepted exception DVD reference must start with DVD-

Scenario: Not Found
Given a store
When the element DVD-999 is searched for
Then not founded DVD

Scenario: TopGun 
Given a store
When the element DVD-TG423 is searched for
Then the resulting element should be Topgun

Scenario: Dirty Dancing
Given a store
When the element DVD-DD878 is searched for
Then the resulting element should be Dirty Dancing

Scenario: Shrek
Given a store
When the element DVD-S765 is searched for
Then the resulting element should be Shrek





!-- Summaries tests
Scenario: TopGun Summary 
Given a store
When the element DVD-TG423 is searched for
Then the resulting summary should be [DVD-TG423] Topgun - All action film

Scenario: Dirty Dancing summary
Given a store
When the element DVD-DD878 is searched for
Then the resulting summary should be [DVD-DD878] Dirty Dancing - Nobody leaves baby in the corner

Scenario: Shrek summary
Given a store
When the element DVD-S765 is searched for
Then the resulting summary should be [DVD-S765] Shrek - Big green monsters, they're just all the rage these days,...


 

