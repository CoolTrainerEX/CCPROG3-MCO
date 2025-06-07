
# Maven

## Compile
```
mvn compile
```

## Execute
```
mvn package
java -cp .\target\mco-1.0-SNAPSHOT.jar com.ccprog3.app.App
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
java -cp .\target\manual\classes\ App
```

## Javadoc
```
javadoc .\src\main\java\com\ccprog3\*.java -d .\target\manual\site\
```
