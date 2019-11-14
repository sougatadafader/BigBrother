# BigBrother

## HEROKU URL
 **https://bigbro-server.herokuapp.com/**

## API Documentation
The REST service should provide a number of endpoints, as follows:


### User Endpoints

**POST** ```api/systemuser``` 
 <br /> 
@required body (example) : 
``` { "username":"Shawn", "password":"shawn@123", "email":"shawn.mendes@gmail.com"}```

>Register as an System User
 - - - -
 
 **POST** ```api/login``` 
 <br /> 
@required body (example) : 
``` { "username":"Shawn", "password":"shawn@123"}```

>Login as an User
 - - - -
 
  **POST** ```api/logout``` 
 <br /> 
@required Http Session : 
> Ends the current logged in session.
- - - -
 
**GET** ```api/users``` 
>returns all the users.
 - - - -

**GET** ```api/profile```
<br /> 
>returns the current logged in User.
 - - - -

**PUT** ```api/user/{userId}```
<br /> 
@required path variable (example) :
``` 11```
>Replaces the current values with the updated ones. 
 - - - -
 
 ### Dependent Endpoints

**POST** ```api/dependent``` 
 <br /> 
@required body (example) : 
``` { "firstName":"Wayne", "lastName":"Rooney", "image":"Near Northeastern University", "zipcode":"02120"}```

>Create a dependent
 - - - -
 
 **GET** ```api/dependents``` 
>returns all the dependents listed by all users.
 - - - -
 
 
**PUT** ```api/dependent/{dependentId}```
<br /> 
@required path variable (example) :
``` 11```
>Replaces the current values with the updated ones.
(Note - Enabled value cannot be changed)
 - - - -

### Campaign Endpoints

**POST** ```/api/dependent/{dependentId}/campaign``` 
 <br /> 
@required body (example) : 
``` { "header":"Help Needed", "text":"21 year old Mark needs your help", "imageUrl":"imageUrl", "targetValue":"10000"}```

>Create a Campaign
 - - - -
 
 **GET** ```/api/campaigns``` 
>returns all the campaigns listed by all users.
 - - - -
 
 
**GET** ```/api/campaign/{campaignId}```
<br /> 
@required path variable (example) :
``` 11```
>Fetches the particular campaign.
 - - - -
 
 **PUT** ```api/campaign/{campaignId}```
<br /> 
@required path variable (example) :
``` 11```
>Replaces the current values with the updated ones.
(Note - Enabled value cannot be changed)
 - - - -
