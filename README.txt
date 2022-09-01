A web application that allows you to:
    Perform user registration.
    Perform user authorization.
    Maintaining a list of manufacturers and products.
    Viewing lists of products and manufacturers.
    Provides an opportunity to use CRUD operations on the following objects:
        roles,
        User,
        Product,
        Manufacturer.

The instruction is valid as of 2022.09.01.
1. Download the repository from github as a compressed file.
    In the master branch, in the root of the repository, click on Code.
    In the drop-down menu, click on Download ZIP.
2. Place the downloaded file in the desired folder to deploy the project.
    Create a new folder, for example shop.
    Place the downloaded file in this folder.
    Unzip all files from JD5HW8SergeyHodak-master to the shop folder.
    You can then extract the zip file.
3. After launching Intellij IDEA, open the project from the shop repository.
    File/Open.../ select the shop folder/ press OK.
    Click Trust Project.
4. We are waiting for Intellij IDEA to download everything necessary for working with the project from the Internet.
5. Starting the program.
    Open the Application class located at src/main/java/goit/ Application .java
    Click on the start symbol, opposite the line number 7 or 8 (it doesn't matter) select run.
6. Wait for the program to assemble and run.
    A sign of this should be two lines at the end, one of which will tell you which localhost you are assigned to, the default being 8080.
    For example: "Tomcat started on port(s): 8080 (http) with context path".
7. Open a browser and follow the link.
    Link localhost:8080
8. Log in as an administrator to configure the app.
    login: admin
    password: admin
9. Role
    here you can (available only to a user with the ADMIN role):
        register new roles,
        edit existing roles,
        remove unnecessary roles.
10. User
    here you can (available only to a user with the ADMIN role):
        register a new user (you can set several roles, separating them with a comma),
        edit existing users (separate roles with a comma),
        delete users.
11. Product
    here you can (available only to a user with the ADMIN role):
        registration of new goods with the possibility of choosing a manufacturer from the drop-down list of registered ones,
        edit,
        remove.
    For all other roles, view records only.
12. Manufacturer
    here you can (available only to a user with the ADMIN role):
        to register new producers,
        edit existing,
        delete existing ones.
    For all other roles, view records only.
13. There is a button for changing the profile on the HOMEPAGE page.