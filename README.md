
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
javac .\src\main\java\com\ccprog3\*.java -d .\target\manual\classes\
```

## Execute
```
java -cp .\target\manual\classes\ com.ccprog3.App
```

## Javadoc
```
javadoc .\src\main\java\com\ccprog3\*.java -d .\target\manual\site\
```
