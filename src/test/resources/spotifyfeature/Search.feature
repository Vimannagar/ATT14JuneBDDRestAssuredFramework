Feature: Search song
Scenario: Search a song
Given Get a search song payload
| songname | type | artist |
| Teri Ore | track | Mayur Puri |
When user calls with GET request
Then API executes with status code as 200