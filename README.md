# Cloud Native

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
      
# Step 03 - Scouter

Scouter 설치

    wget https://github.com/scouter-project/scouter/releases/download/v2.12.0.1.SNAPSHOT/scouter-all-2.12.0.1.SNAPSHOT.tar.gz
    
    
Scouter 압축풀기
    
    tar -zxvf scouter-all-2.12.0.1.SNAPSHOT.tar.gz
    
    
Application에 agent 설정하기

    vi pom.xml
    
    <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <jvmArguments>
            -javaagent:/home/ubuntu/scouter/agent.java/scouter.agent.jar
            -Dscouter.config=/home/ubuntu/scouter/agent.java/conf/scouter.conf
            -Dobj_name=WAS-01
            </jvmArguments>
        </configuration>

Scouter start

    /home/ubuntu/scouter/server/startup.sh
    
Application 기동
    
    ./mvnw spring-boot:run

Scouter Client 설치(윈도우)

    https://github.com/scouter-project/scouter/releases/
    
    scouter.client.product-win32.win32.x86_64.zip 다운로드
    

Scouter Client start (윈도우 기준)
  
    server Address : 스카우터를 설치한 서버 아이피와 포트(127.0.0.1:6100)
    ID : 초기에는 admin으로 설정되어있다.
    Password : 패스워드 또한 아이디와 동일하게 기본값으로 admin으로 설정되어있다.


# Step 04 - Prometheus

Prometheus 설치    

    wget https://github.com/prometheus/prometheus/releases/download/v2.20.1/prometheus-2.20.1.linux-amd64.tar.gz

Prometheus 압축풀기

    tar zxvf prometheus-2.20.1.linux-amd64.tar.gz

Prometheus Config Check
    
    $ cd prometheus-2.20.1.linux-amd64/
    $ nano prometheus.yml
    
    # my global config
    global:
      scrape_interval:     10s # Set the scrape interval to every 10 seconds. Default is every 1 minute.
      evaluation_interval: 10s # Evaluate rules every 10 seconds. The default is every 1 minute.
      # scrape_timeout is set to the global default (10s).

    # A scrape configuration containing exactly one endpoint to scrape:
    # Here it's Prometheus itself.
    scrape_configs:
    # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
    - job_name: 'prometheus'

    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.                                                                                        
    static_configs:
    - targets: ['localhost:9090']

