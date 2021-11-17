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

    global (기본적인 전역설정)
    scrape_interval : 얼마나 자주 메트릭(targets)을 수집할지 설정. 기본 1분
    evaluation_interval : 얼마나 자주 규칙(rules)을 평가할지 설정. 기본 1분
    scrape_configs (메트릭을 수집할 엔드포인트로 여기서는 prometheus 서버 자신을 가리키는 설정을 했다. 여러개 설정이 가능하다.)

    job_name : 잡 이름 설정
    static_config : 긁어하는 방법에 대한 목표 및 파라미터 세트를 지정.
    
Prometheus 기동
    
    $ ./prometheus
    
Prometheus 확인

    http://자신의ip주소:9090/
    
# Step 05 - Node Exporter

Node Exporter 설치

    wget wget https://github.com/prometheus/node_exporter/releases/download/v1.1.2/node_exporter-1.1.2.linux-amd64.tar.gz
    tar xvfz node_exporter-1.1.2.linux-amd64.tar.gz
    cd node_exporter-1.1.2.linux-amd64/
    
Node Exporter 실행
    
    ./node_exporter

# Step 06 - Grafana

Grafana 설치
    
    wget https://dl.grafana.com/oss/release/grafana_7.5.2_amd64.deb 
    sudo dpkg -i grafana_7.5.2_amd64.deb 

Grafana 실행
    
    sudo service grafana-server start 

실행 상태 확인 
    
    sudo service grafana-server status
