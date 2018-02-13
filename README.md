# api-Spring_Boot

REST APIs implemented using Spring Boot and MongoDB

REST Endpoints configured as of today ->

a.) /marketvote/article/create (takes a list of articles as an input JSON)
b.) /marketvote/article/get/all (all the articles - output JSON)
c.) /marketvote/article/get/{id} (getting an article by ID)
d.) /marketvote/article/conblurb/click/ (adding click count for conblurb, input JSON -> article Ojbect with the updated click value)
e.) /marketvote/article/problurb/click/ (adding click count for problurb, input JSON -> article Ojbect with the updated click value)

I'm adding the sample JSONs files to the repository

Steps to import the project (Assuming Gradle plugin is already installed) 

a.) Import the project as a "Gradle Project" in the IDE. 
b.) All the dependencies have already been added. 
c.) Run the spring boot application with mongo instance running. 
d.) Check the sample JSON files to get the correct JSON. 



