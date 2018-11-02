# github-repo-proxy
## Description
Proxy Service which purpose is fetching repositories from github.

Application call public github api to fetch information about repository with given name for specified user. Calling api has been performed with help of Retrofit library.

Project contains integration tests written using RestAssured library. To simulate real api calls GihubClientMock has been created using Netty Server which starts on specified port, for this case additional configuration has been prepared prevent calling real api.(See file *application.yml* in *src/test/resources*)

Project is starting on port **9880**. (Example path: *http://localhost:9880/api/repositories/michalcholewinski/css-training*)

See documentation to get information how to use Api.

More detailed description available [here](https://eagertoit.com/2018/11/02/github-repository-proxy/).

## Setup
To build application run command *mvn clean install* from root directory of project.

To start server run: *mvn spring-boot:run* 

## Documentation
REST API documentation is available under [localhost:9880/swagger-ui.html](http://localhost:9880/swagger-ui.html)

Above documentation has been created with help of Swagger2 library.

## Technologies
Technologies used in project:
* Java 8
* Spring Boot 2
* Retrofit
* Lombok
* Mapstruct
* Rest assured
* Netty Server

## Author
**Michał Cholewiński**, Software Engineer  
[cholewinskimichal.com](http://cholewinskimichal.com)    
[Eager To IT](https://eagertoit.com)

