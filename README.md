# BigBrother

## HEROKU URL
 **https://bigbro-server.herokuapp.com/**

## API Documentation
The REST service should provide a number of endpoints, as follows:

### Dependent Endpoints

**POST** ```api/dependent``` 
 <br /> 
@required body (example) : 
``` { "firstName":"Wayne", "lastName":"Rooney", "landmark":"Near Northeastern University", "zipcode":"02120"}```

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
