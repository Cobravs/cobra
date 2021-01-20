#!/bin/bash

echo '开始安装编译环境'
yum install -y epel-release centos-release-scl scl-utils
yum install -y devtoolset-7

echo '开始安装freeswitch'
cd $(dirname $0)
scl enable devtoolset-7 './freeswitch1-10-3.sh'
