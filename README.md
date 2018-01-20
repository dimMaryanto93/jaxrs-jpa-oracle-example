# JAX-RS with JPA oracle

- jax-rs web service implement Java EE 7
- JPA eclipselink with Annotation

## Installation oracle

Clone repository 

Open terminal lalu `cd path-to-repository/OracleDatabase/dockerfiles/` download [installer oracle xe](http://download.oracle.com/otn/linux/oracle11g/xe/oracle-xe-11.2.0-1.0.x86_64.rpm.zip) 
simpan di folder `11.2.0.2` 

![image location](docs/images/docker-image.png)

```bash
./buildDockerImage.sh -v 11.2.0.2 -x -i
```

```bash
docker image ls 
```

![image location](docs/images/docker-image-ls.png)

## Installation Payara server

Pull payara from `docker pull payara/server-full`

```bash
docker container ls
```

![docker](docs/images/payara-docker-ls.png)

## Run & Using docker

Startup...

```bash
docker-compose up -d
```

Setelah `oracledb` startup is done... setup your system/dba/sys password using command

```bash 
docker exec -ti <container-id-oracledb> bash /opt/alter-user-oracle.sh
```

Cleanup...

```bash
docker-compose down
```

