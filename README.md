# Shortening-Service
Converts userdefined url to short/custom url
# Requirement
<li>Take a URL and return a much shorter URL</li>
<ol>-The input URL format must be valid</ol>
<ol>-Maximum character length for the hash portion of the URL is 6</ol>
<ol>-The Service should return a meaningful message with a suitable status code</ol>
<li> Take a short URL and redirect to the original URL</li>
<li>Allow the users to pick custom shortened URL</li>
<li>Usage statistics for site owner</li>

# Swagger
[Swagger](http://localhost:8081/shortening-service/swagger-ui/index.html)<br />
**Note:**
<li>localhost 8081 refers to the port on which spring boot application runs</li>
<li>Without running the spring boot application swagger will not work</li>

# Services
[Post-Url which needs to be shortend](http://localhost:8081/shortening-service/v1/shortening) <br/>
[Post-Url which needs to be shortend based on custom input](http://localhost:8081/shortening-service/v1/customShortening) <br/>
[Get-Url based on shortend url](http://localhost:8081/shortening-service/v1/shortening?URL=https%3A%2F%2Fgroup.mercedes-benz.com%2Fkarriere%2Fberufserfahrene%2Fdirekteinstieg%2)<br/>
[Get-Url to fetch last 24hrs activity of user](http://localhost:8081/shortening-service/v1/userActivity)

