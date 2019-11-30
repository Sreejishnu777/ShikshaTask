Feature: Hyperlinks verification in Documentation page

@HyperlinksTest
Scenario: Verification of hyperlinks internal to documentation page
Given User loads "https://developer.here.com/documentation"
Then verify page loads properly and angular is initialized
And verify all links on page internal to documentation site are not broken and for each link if page loads and angular is initialized





