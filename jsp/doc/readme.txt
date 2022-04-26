

위에서 실행되지 못하는 이유는 Maven이 최상위 Root 폴더 기준으로
해당 위치를 찾기때문에, 해당 모듈을 기준으로 찾도록 설정해줘여한다.

Edit Configuration..에서
Working Directory에 아래 값을 넣으면 해결!
$MODULE_WORKING_DIR$

----

Intellij에서 jsp 프로젝트 인식시키는 방법
1. Open Module Settings > Modules > 프로젝트 선택 > Web 선택
2. Web Resource Directories의 경로는 webapp 경로로 설정
3. Maven > Plugin > spring-boot > spring-boot:run 실행