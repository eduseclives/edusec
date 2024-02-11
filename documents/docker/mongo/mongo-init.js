conn = new Mongo();

db = conn.getDB("edusecdb");

db.createUser({
    user: 'adminusr',
    pwd: 'password',
    roles: [
        { role: 'readWrite', db: 'edusecdb' }
    ]
})

db.createCollection('test');
db.test.insertOne(
  {
    name: 'ping .... pong'
  }
);