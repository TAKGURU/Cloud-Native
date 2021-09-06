# Spring-Devops

# Step 01 - Springboot

STS4 다운로드 및 설치(https://spring.io)
** 사전에 자바 설치

git clone https://github.com/TAKGURU/Spring-Devops.git
** 사전에 git 설치

# Step 02 - nGrinder

nGrinder(controller) 설치 - 웹 기반의 GUI 시스템으로 테스트 전반적인 작업이 이 컨트롤러에 의해서 작동

    $ mkdir ngrinder
    $ cd ngrinder
    $ wget https://github.com/naver/ngrinder/releases/download/ngrinder-3.5.2-20200929/ngrinder-controller-3.5.2.war
    $ java -jar ngrinder-controller-3.5.2.war --port 7070

nGrinder Agent 설치 - 부하를 발생

    $ tar -xvf ngrinder-agent-3.5.2-localhost.tar
    
    $ cd ngrinder-agent
    $ vim __agent.conf
    
    common.start_mode=agent
    agent.controller_host=localhost
    agent.controller_port=16001
    agent.region=NONE
    
    $ ./run_agent.sh
       
