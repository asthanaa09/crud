-----------------------------------
Create User:- Api URL: /api/user
-----------------------------------

{

	"firstName": "abx",
	"lastName": "xyz",
	"userName": "abc@ham",
	"password": "adasas",
	"email": "abx@gmail.com",
	"mobile": "1234567890",
	"pincode": 123456"",
}

----------------------------
Update user: Api - /api/user/delete
----------------------------
To update user use same json fromat as create user api and send user id as parameter
e.g :  /api/user?id=1

---------------------------------
Delete User: API Url: /api/user
----------------------------------

To delete user follow the below URLs

Soft Delete: /api/user/delete?id=1

Hard Delete: /api/user/delete?id=2&deleteFromDb=true

--------------------------------------------------------
Search User by Parameter - First Name, Last Name or  pincode
API Endpoint - /api/user/query
---------------------------------------------------------

Example 1 : /api/user/query?firstName=[value]
Example 2: /api/user/query?firstName=[value]&pincode=[value]

-------------------------------------------------------------------

------------------------
All Users Records : 
-----------------------
API End point - /api/user/all
