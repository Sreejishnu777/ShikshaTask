$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("DocumentationLinks.feature");
formatter.feature({
  "line": 1,
  "name": "Hyperlinks verification in Documentation page",
  "description": "",
  "id": "hyperlinks-verification-in-documentation-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4415141300,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Verification of hyperlinks internal to documentation page",
  "description": "",
  "id": "hyperlinks-verification-in-documentation-page;verification-of-hyperlinks-internal-to-documentation-page",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@HyperlinksTest"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "User loads \"https://developer.here.com/documentation\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "verify page loads properly and angular is initialized",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "verify all links on page internal to documentation site are not broken and for each link if page loads and angular is initialized",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "https://developer.here.com/documentation",
      "offset": 12
    }
  ],
  "location": "DocumentationLinksSteps.user_loads_something(String)"
});
formatter.result({
  "duration": 3897666200,
  "status": "passed"
});
formatter.match({
  "location": "DocumentationLinksSteps.verify_page_loads_properly_and_angular_is_initialized()"
});
formatter.result({
  "duration": 276590800,
  "status": "passed"
});
formatter.match({
  "location": "DocumentationLinksSteps.verify_all_links_on_page__internal_to_documentation_site_are_not_broken_and_for_each_link_if_page_loads_and_angular_is_initialized()"
});
formatter.result({
  "duration": 326253520000,
  "status": "passed"
});
formatter.after({
  "duration": 647661600,
  "status": "passed"
});
});