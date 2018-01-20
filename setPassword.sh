#!/bin/bash

ORACLE_PWD=$1

echo "update password system/sys !";

su -p oracle -c "sqlplus / as sysdba << EOF
      ALTER USER SYS IDENTIFIED BY "$ORACLE_PWD";
      ALTER USER SYSTEM IDENTIFIED BY "$ORACLE_PWD";
      exit;
EOF"