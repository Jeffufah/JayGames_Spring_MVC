The database was originally intended to be placed on x10hosting, a cloud host service for web domains.
Basically, our team wanted a live version of the website to be available for view without the need
to look at the files on GitHub.

I've had prior experience with x10hosting from my internship at Wilmington University so querying the
MySQL database on the backend wasn't too tricky since I had sample code to work with. The x10Hosting Database 
Access folder contains the php scripts used to query the database. It's by no means complete. I spent most of my time on the project
making the Plot Four game networkable. I need to integrate prepared statements and look into password salt hashing
to protect users.

I exported the database in its current state as an .sql file and placed it along with a .txt document in this
containing folder. The .txt document contains the queries required to connect the database tables back together.
Also, phpmyadmin has a nice feature called designer view, which lets you drag and drop your tables around for a nice 
view of the schema. I've placed a screenshot of the schema here in this containing folder. Most of these tables
haven't been implemented yet. The project was rather ambitious and we weren't experienced enough to deliver
the product (multiplayer game), and a fully fledged website that implements all of the features this database is designed to support.
I felt that the Plot Four game was more interesting to interact with than the website so I focused most on
that aspect of the project.