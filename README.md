# Room Database

Room is now considered as a better approach for data persistence than SQLiteDatabase. It makes it easier to work with SQLiteDatabase objects in your app, decreasing the amount of boilerplate code and verifying SQL queries at compile time.

## Why use Room?

Compile-time verification of SQL queries. each @Query and @Entity is checked at the compile time, that preserves your app from crash issues at runtime and not only it checks the only syntax, but also missing tables.
Boilerplate code
Easily integrated with other Architecture components (like LiveData)

Room has three main components of Room DB :

* Entity

* Dao

* Database

## 1. Entity
Represents a table within the database. Room creates a table for each class that has @Entity annotation, the fields in the class correspond to columns in the table. Therefore, the entity classes tend to be small model classes that don’t contain any logic.
Entities annotations

Before we get started with modeling our entities, we need to know some useful annotations and their attributes.

@Entity — every model class with this annotation will have a mapping table in DB

* foreignKeys — names of foreign keys

* indices — list of indicates on the table

* primaryKeys — names of entity primary keys

* tableName

### @PrimaryKey — 
As its name indicates, this annotation points the primary key of the entity. autoGenerate — if set to true, then SQLite will be generating a unique id for the column

* @PrimaryKey(autoGenerate = true)
* @ColumnInfo — allows specifying custom information about column
* @ColumnInfo(name = “column_name”)
* @Ignore — field will not be persisted by Room
* @Embeded — nested fields can be referenced directly in the SQL queries.
## 2. Dao
DAOs are responsible for defining the methods that access the database. In the initial SQLite, we use the Cursor objects. With Room, we don’t need all the Cursor related code and can simply define our queries using annotations in the Dao class.
## 3. Database
Contains the database holder and serves as the main access point for the underlying connection to your app’s persisted, relational data.
To create a database we need to define an abstract class that extends RoomDatabase. This class is annotated with @Database, lists the entities contained in the database, and the DAOs which access them.

The class that’s annotated with @Database should satisfy the following conditions:
Be an abstract class that extends RoomDatabase.

Include the list of entities associated with the database within the annotation.
Contain an abstract method that has 0 arguments and returns the class that is annotated with @Dao.
At runtime, you can acquire an instance of Database by calling Room.databaseBuilder() orRoom.inMemoryDatabaseBuilder().
