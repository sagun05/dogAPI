# API Challenge

Design and create a RESTful API that could be used to manage a list of dog images. 

# Requirements

The operations we expect to see would be:

* List all of the available dog pictures grouped by breed
* List all of the available dog pictures of a particular breed
* Vote up and down a dog picture
* The details associated with a dog picture

The information the Dog Breed App needs to function is:

* A URL to a dog picture
* The number of votes a picture has received
* The dog's breed
* Any other information required to identify a specific dog

The Dog Breed App expects the response to be sorted by the number of votes a picture has received.

The API responses must be in JSON.

## Additional Voting Requirements

Each client is allowed to vote once for any particular dog picture.

# REST API
The REST API to the api Challenge app is described below.

## Get list of all the available dog pictures grouped by breed

### Request

`GET /test-app/service/dogbreeds/`

    curl -i -H 'Accept: application/json' http://localhost:8080/test-app/service/dogbreeds
    
### Response

    HTTP/1.1 200 OK
    Date: Mon, 22 Apr 2019 18:56:54 GMT
    Status: 200 OK
    Transfer-Encoding: chunked
    Content-Type: application/json    

    []

## Get List of all the available dog pictures of a particular breed

### Request

`GET /test-app/service/dogbreed/{breed}/`

    curl -i -H 'Accept: application/json' http://localhost:8080/test-app/service/dogbreed/BullDog
    
### Response

    HTTP/1.1 200 OK
    Date: Mon, 22 Apr 2019 18:56:54 GMT
    Status: 200 OK
    Content-Type: application/json    
    Content-Length: 330

    {"breed_id":4,"breedName":"BullDog","imgList":[{"image_id":88,"imageUrl":"http://i.imgur.com/r2uqgVQ.jpg","imageIdentity":"r2uqgVQ","vote":0},{"image_id":89,"imageUrl":"http://i.imgur.com/4SZqjlo.jpeg","imageIdentity":"4SZqjlo","vote":0},{"image_id":90,"imageUrl":"http://i.imgur.com/glT1i6n.jpg","imageIdentity":"glT1i6n","vote":0}]}

## Update, Vote up and down a dog picture

### Request

`POST /test-app/service/votedogimage/`

    curl -v -H "Content-Type:application/json" -X POST http://localhost:8080/test-app/service/votedogimage -d '{"id": 88,"breedName": "BullDog","dogImage": "http://i.imgur.com/r2uqgVQ.jpg","imageIdentity": "glT1i6n","vote": 1}'
    
### Response

    HTTP/1.1 200 OK
    Date: Mon, 22 April 2019 11:40:30 PST
    Status: 200 OK    
    Content-Type: application/json
    Content-Length: 114

     upload completely sent off: 114 out of 114 bytes

## Get The details associated with a dog picture

### Request

`GET /test-app/service/dogimage/{imageIdentity}/`

    curl -i -H 'Accept: application/json' http://localhost:8080/test-app/service/dogimage/glT1i6n
    
### Response

    HTTP/1.1 200 OK
    Date: Mon, 22 Apr 2019 18:56:54 GMT
    Status: 200 OK
    Content-Type: application/json    
    Content-Length: 330

    {"image_id": 90,"imageUrl": "http://i.imgur.com/glT1i6n.jpg","imageIdentity": "glT1i6n","vote": 3}

# Setup

We provide you with a starter Spring Boot project. The project is already configured go use Spring MVC and talks to an 
in memory HSQLDB to store the results. Jackson is already included to provide JSON serialization and deserialization.

## Building

```
$ mvn package
```

## Running

You can run the app through Maven:


```
$ java -jar target/api_interview-0.1.0.jar
```
