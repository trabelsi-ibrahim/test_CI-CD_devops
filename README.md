# test_CI-CD_devops
## 1. Créer un dépôt GitHub :
• Créez un nouveau dépôt sur GitHub pour votre projet d'application.
![1_new_repo](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/91115968-5af7-42cc-883f-aa5c4520c627)

![2_repo](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/b7097d63-8a90-4549-a3e2-4adf6160d073)

• Initialisez un dépôt Git local sur votre ordinateur et connectez-le à votre dépôt GitHub.
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/c7224340-d80e-40eb-abd4-de88eb5d2b5d)
```
git init
```
```
git remote add origin https://github.com/trabelsi-ibrahim/test_CI-CD_devops.git
```

## 2. Développez votre application :
- Choisissez Java ou Python comme langage de programmation et configurez l'environnement de développement en conséquence.
- Structurez le code de votre projet en packages, modules ou classesappropriés.
- Implémentez les fonctionnalités principales de votre application.
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/d75f4f40-7fb8-4068-8278-ca666382013a)
```java
package Clacul;
public class Calculatrice {
    public int additionner(int a, int b) {
        return a + b;
    }

    public int soustraire(int a, int b) {
        return a - b;
    }

    public int multiplier(int a, int b) {
        return a * b;
    }

    public int diviser(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division par zéro est impossible");
        }
        return a / b;
    }
}
```
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/fe1e20d9-1562-4ed5-96b3-aa47ed293fe0)
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/f7095ad4-0f5b-42c3-929a-1fd5fc84e660)

```
git add .
git commit -m "commit"
git push origin master

```
## 3. Créer un pipeline CI/CD à l'aide de GitHub Actions :
- Accédez à l'onglet "Actions" de votre dépôt GitHub :
  - Ouvrez votre dépôt GitHub dans votre navigateur web.
  - Dans la barre de navigation supérieure, cliquez sur l'onglet "Actions".
   ![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/ac6e2a9f-4ff9-461a-a4e8-a1c9afd830c2)

- Cliquez sur "Nouveau workflow" :
  - Dans la page "Actions", cliquez sur le bouton vert "Nouveau workflow" situé à droite.
- Sélectionnez l'option "Utiliser un fichier YAML" :
  - Une liste de modèles de workflow apparaîtra, mais pour créer un workflow personnalisé, cliquez sur "Débuter avec un fichier YAML vide".
  - Cela ouvrira un éditeur de texte où vous pourrez commencer à rédiger votre fichier YAML.
    ![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/7c22f4cc-2f19-4ced-92e7-12cf073e9f69)

- Éditez votre fichier YAML :
- Vous pouvez nommer votre fichier YAML comme vous le souhaitez, par exemple : .github/workflows/ci-cd.yml.
- Commencez à rédiger votre workflow en utilisant la syntaxe YAML.

## 4. Définir le pipeline CI/CD dans le fichier de workflow :
- Structurez le fichier de workflow en jobs distincts :
- Build (Construction) : Ce job doit compiler ou interpréter le code de votre application.
- Test : Ce job doit exécuter des tests automatisés pour votre application.
- Déploiement : Ce job (facultatif) doit déployer votre application dans un environnement de production.
- Pour chaque job, définissez les étapes à exécuter :
  - Spécifiez les commandes à exécuter pour chaque étape.
  - Utilisez des variables d'environnement pour passer des paramètres et des configurations.
    ![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/9baffb9b-ac8c-4c43-bbee-0eb35ac0d0ad)
```YAMAL
name: CI

on:
  push:
    branches: [ "master" ]
  workflow_dispatch:

jobs:
  build:
    runs-on: windows-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: '11'
          distribution: 'adopt'
          architecture: 'x64'
          server-id: 'github'
          server-username: ${{ github.actor }}
          server-password: ${{ secrets.GITHUB_TOKEN }}
          overwrite-settings: true

     
      - name: Build project
        run: javac -cp .:junit-platform-console-standalone.jar src/*.java

      - name: Search for JAR file
  run: dir /s /b junit-platform-console-standalone.jar


      - name: Run tests
        run: java -jar junit-platform-console-standalone.jar --class-path . --scan-class-path

```

## 5. Implémenter des tests automatisés :
- Choisissez un framework de test adapté à votre langage de programmation (par exemple, JUnit pour Java, pytest pour Python).
- Écrivez des tests unitaires qui couvrent les composants et les fonctionnalités individuels de votre application.
- Intégrez le framework de test dans votre pipeline CI/CD.
  
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/5fa497aa-fed4-4a57-a6d2-b02dda51d135)
```java
package Calcul;
import Clacul.Calculatrice;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatriceTest {

    @Test
    public void testAdditionner() {
        Calculatrice calc = new Calculatrice();
        assertEquals(5, calc.additionner(2, 3));
    }

    @Test
    public void testSoustraire() {
        Calculatrice calc = new Calculatrice();
        assertEquals(1, calc.soustraire(4, 3));
    }

    @Test
    public void testMultiplier() {
        Calculatrice calc = new Calculatrice();
        assertEquals(12, calc.multiplier(3, 4));
    }

    @Test
    public void testDiviser() {
        Calculatrice calc = new Calculatrice();
        assertEquals(2, calc.diviser(8, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivisionParZero() {
        Calculatrice calc = new Calculatrice();
        calc.diviser(5, 0);
    }
}


```
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/d3633492-7a47-46d5-9674-fc3474ef5916)

## 6. Commit et push des modifications :
-  Validez vos modifications de code, y compris le fichier de workflow CI/CD et les scripts de test, dans votre dépôt Git local.
- Poussez les modifications validées vers votre dépôt GitHub.

  ![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/e49ed15f-1de0-4118-9843-74d97748d62d)
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/78aff333-96a2-4879-bce8-5e3784500cd5)

```
git pull origin master --allow-unrelated-histories

```

## 7. Surveiller et affiner :
- Observez l'exécution de votre pipeline CI/CD dans l'onglet GitHub Actions.
- Analysez les résultats des tests pour identifier et corriger tout problème.
- Améliorez continuellement le code de votre application, les tests et le pipeline CI/CD.
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/2271f50f-c022-4da9-80e8-2a5c94ae1962)
![image](https://github.com/trabelsi-ibrahim/test_CI-CD_devops/assets/121525548/54fc70ad-17ae-410d-be98-23b4af0c0373)

