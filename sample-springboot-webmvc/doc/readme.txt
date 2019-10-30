Intellij에서 Springboot를 이용하여 Jsp를 실행하기 위해서는 아래 설정이 필요합니다.
Maven > Plugin > spring-boot > spring-boot:run

위에 방법으로 실행하지 않을 경우 jsp 파일을 찾지 못합니다.
Intellij 버그로 생각됩니다.

---

위에서 실행되지 못하는 이유는 Maven이 최상위 Root 폴더 기준으로
해당 위치를 찾기때문에, 해당 모듈을 기준으로 찾도록 설정해줘여한다.

Edit Configuration..에서
Working Directory에 아래 값을 넣으면 해결!
$MODULE_WORKING_DIR$