# tp-salle-de-sport

Mini-projet Java utilisé pour le TP (tests + SonarQube).

Badge Sonar (remplacer le lien si besoin):

![Sonar Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=tp-salledesport&metric=alert_status)

Comment lancer

- Tests :
```powershell
mvn -DskipTests=false test
```
- Sonar (définir d'abord le token dans la session PowerShell, par exemple ` $env:SONAR_TOKEN = 'PASTE_TOKEN_HERE'` ) :
```powershell
mvn -X sonar:sonar
```

Rapport
- Le rapport 1 page est dans `docs/rapport-sonar.md`. Placez vos captures sous `docs/images/` et convertissez le Markdown vers PDF si nécessaire.
