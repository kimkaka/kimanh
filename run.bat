set ProjectPath=C:\Users\KimKaka\eclipse-workspace\Kimanh
cd %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\lib\*
cd %classpath%
java org.testng.TestNG testng.xml
pause