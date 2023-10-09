## SleepIsDead 

### Repositories  [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FSleepIsDead%2F.github&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

---
#  Travel Maker(Sleep is Dead)
> **MTVS-SERVER-SleepIsDead** <br/> **개발기간: 2023.08.01 ~ 2022.11.30**

---
## Team Member Introduction
| 권은지                                                                               |김태현| 김지수                                                                               |
|-----------------------------------------------------------------------------------|-------------------------------------------------|-----------------------------------------------------------------------------------|
| <img width="160px" src="https://avatars.githubusercontent.com/u/139085498?v=4" /> |<img width="160px" src="https://avatars.githubusercontent.com/u/136583226?v=4" />| <img width="160px" src="https://avatars.githubusercontent.com/u/122511847?v=4" /> |
| <center>[@kej2971](https://github.com/kej2971)                                    | <center>[@taedyyyyy](https://github.com/taedyyyyy)| <center>[@jisoo9603](https://github.com/jisoo9603)|
| <center>FRONTEND                                                                  |<center>BACKEND|<center>FRONTEND|

| 이유열                                                                              |정재민|
|----------------------------------------------------------------------------------|-------------------------------------------------|
| <img width="160px" src="https://avatars.githubusercontent.com/u/84696773?v=4" /> |<img width="160px" src="https://avatars.githubusercontent.com/u/125876896?v=4" />|
| <center>[@youyeul301](https://github.com/youyeul301)                             |<center>[@devJaem](https://github.com/devJaem)|
|<center>FRONTEND|<center>BACKEND|
---
# [BACKEND]
--- 
## Getting Started Guide
### 요구사항
For building and running the application you need:

- [MySQL8.0.33](https://dev.mysql.com/downloads/mysql/)
- [JAVA11.0.15](https://github.com/ojdkbuild/ojdkbuild)
- [Node.js](https://nodejs.org/ko)

---

## Stacks 🐈

### Environment
<img src="https://img.shields.io/badge/Intelii J-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<img src="https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">

### Config
![MIRO](https://img.shields.io/badge/miro-050038?style=for-the-badge&logo=miro&logoColor=white)

### Development
![springboot](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MYSQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)


### 소통수단
<img src="https://img.shields.io/badge/discord-5865F2?style=for-the-badge&logo=discord&logoColor=white">
<img src="https://img.shields.io/badge/kakaotalk-FFCD00?style=for-the-badge&logo=kakaotalk&logoColor=white">

---
## 중간 점검(backend 입장) 📺
1. 기본적인 코드 작성 (Context, 폴더구조, security 등..)
   - jwt, 기본 틀 잡는데만 2주이상 시간이 소요됨.. (시간 투자에비해 결과물이 안나옴) 
   - 엔티티설계 / security,jwt로 분리했던 판단이 오판이었음.
   - 이후 swagger 3.0.0 적용시 401 에러가 계속 나서, 시간을 많이 잡아먹음, 시큐리티 쪽에서는 권한을 풀어놨지만,<br> jwt 필터쪽에서 예외처리를 하지못함
   - 엔티티 설계 + swaager 이후로 security를 씌웠다면 훨씬 빠른 작업이 가능했다고 예상됨<br><br>
   **결론 : 작업 분배시에, 개인의 욕심이 아니라 팀적 능률, 개발의 전체적인 흐름을 고려해서 분배를 진행할 예정**
<br><br>
2. 전체적인 코드 작성(엔티티 설계, 서비스 구현)
   - 시큐리티 이후 전체적인 시간이 늘어지다 보니, test 코드가 미비된 상태임
   - 향후 CI/CD 구현을 위해서라도 디테일하게 작성할 필요가 있음<br><br>
   **결론 : 융합프로젝트 도중에도 기능을 구현하기 위해서, Context단위로 test코드 작성후 관리할 예정**
<br><br> 
3. 소통의 불편함
   - 프론트엔드, 백엔드를 나누어서 작업하는게 처음이다보니, 각자 맡은 영역 이외에 관심이 적어짐 
   - 설계시에 소통이 된 부분과 실제 구현이 된 부분사이에 조금 차이점이 있음. <br><br>
   **결론 : 온라인 회의로는 한계가 명확함, 작은 결정은 온라인으로 하더라도 큰 결정은 오프라인 회의가 필요**
<br><br>
4. 주제 선정시의 이슈
   - 처음 주제 선정시에 시간소요가 길어서 마음이 급해지다보니, 전체적으로 프로젝트가 중구난방이됨.
   - 중간에 방향성이 흔들리기도하고, 설계 자체를 다시 엎어서 새로 하다보니, 충분히 소통할 시간이 부족<br>
   - 일단 개발부터 시작한 사이드 이팩트가 여기저기 퍼짐
   - 향후 추가기능 구현시 필히 회의를 거처서 문서로 요구사항을 정리할 필요가 있음.<br><br>
   **결론 : 회의록이나, 주요내용 정리를 해서, 의견과, 개발에 대한 근거를 확실히 해야함.**
<br><br>
---
## 개선사항
1. Login
   Security Filter 개선하여 토큰없이도 접근 가능(로그인, 회원가입)
   토큰 provider 개선하여 토큰 획득과정 간소화
2. Member
   맴버엔티티에 개인정보 최소화
   회원가입시, 소셜아이디1개가 1개의 개정으로 분리됨 (추후 카카오 테스트 예정)


## directory tree
```bash

           
```
 
