#!/bin/bash
until 
    su -p oracle -c "sqlplus / as sysdba << EOF
        ALTER USER SYS IDENTIFIED BY admin;
        ALTER USER SYSTEM IDENTIFIED BY admin;
        ALTER USER HR IDENTIFIED BY hr ACCOUNT UNLOCK;
        exit;
      EOF"; 
do 
      >&2 echo "Oracle database sleeping..."
      sleep 1
done