Most of these php scripts are used by Plot Four server hosts so that they can maintain their
presence in the database. The clients using the Plot Four game are meant to see servers that
intend random players to be able to access their game.

I'd experienced a bug with creating new MySQL users to access the database and did not want to
spend too much time troubleshooting an issue with a prototype. So in the mean time, I import
the credentials to access the database from a php script that's not in the public directory.
I still need to implement measures to protect against SQL Injection and look into salt hashing 
passwords.

Most of these php scripts reconcile user credentials with the database before performing the task
they were inteded to perform. I wanted to keep the scripts short and readable so I made each
script with the intent of only performing what its name implies.