
# Maven

## Compile
```
mvn compile
```

## Execute
```
mvn package
java -jar .\target\mco-1.0-SNAPSHOT.jar
```

## Javadoc
```
mvn site
```

## Clean old articacts
```
mvn clean
```

# Java
.\target\manual\ is any directory. It is just in .\target so that it will be in the `.gitignore`.

## Compile
```
javac -sourcepath .\src\main\java\ -d .\target\manual\classes\ .\src\main\java\com\ccprog3\App.java
```

## Execute
```
java -cp .\target\manual\classes\ com.ccprog3.App
```

## Javadoc
```
javadoc -sourcepath .\src\main\java\ -d docs com.ccprog3 com.ccprog3.coffee com.ccprog3.coffeeTruck com.ccprog3.gui com.ccprog3.ingredients
```
