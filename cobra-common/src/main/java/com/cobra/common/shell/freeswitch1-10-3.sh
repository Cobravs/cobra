#!/bin/bash

set -e

echo '安装freeswitch的编译库'
yum install -y https://files.freeswitch.org/repo/yum/centos-release/freeswitch-release-repo-0-1.noarch.rpm
yum-builddep -y freeswitch

echo '下载源码'
cd $(dirname $0)

yum install -y wget unzip bzip2

echo '下载freeswitch'
wget https://codeload.github.com/signalwire/freeswitch/tar.gz/v1.10.3 -t 3 -O freeswitch-1.10.3.tar.gz
tar -xvf freeswitch-1.10.3.tar.gz
mv freeswitch-1.10.3 /usr/local/src

echo '下载libav'
wget https://freeswitch.org/stash/rest/api/latest/projects/SD/repos/libav/archive?format=zip -t 3 -O libav-master.zip
unzip -d libav-master libav-master.zip
mv libav-master /usr/local/src

echo '下载nasm'
wget https://www.nasm.us/pub/nasm/releasebuilds/2.13.03/nasm-2.13.03.tar.gz -t 3
tar -xvf nasm-2.13.03.tar.gz
mv nasm-2.13.03 /usr/local/src

echo '下载libx264'
wget https://ftp.videolan.org/pub/x264/snapshots/x264-snapshot-20180101-2245-stable.tar.bz2 -t 3 --no-check-certificate
tar -xvf x264-snapshot-20180101-2245-stable.tar.bz2
mv x264-snapshot-20180101-2245-stable /usr/local/src

echo '下载mod_g729'
wget https://codeload.github.com/typefo/mod_g729/zip/master -t 3 -O mod_g729-master.zip
unzip mod_g729-master.zip
mv mod_g729-master /usr/local/src

echo '设置组件环境'
echo "/usr/local/lib" >/etc/ld.so.conf.d/usr-local-lib.conf
export PKG_CONFIG_PATH=/usr/local/lib/pkgconfig:${PKG_CONFIG_PATH}

echo '为编译libx264安装nasm'
cd /usr/local/src/nasm-2.13.03
ldconfig
./configure
make
make install

echo '为libav编译libx264'
cd /usr/local/src/x264-snapshot-20180101-2245-stable
ldconfig
./configure --enable-shared --enable-static --disable-opencl
make
make install

echo '为freeswitch的mod_av编译libav'
cd /usr/local/src/libav-master
ldconfig
./configure --enable-shared --enable-libx264 --enable-gpl --extra-cflags=-fPIC
make
make install

echo '编译安装freeswitch'
cd /usr/local/src/freeswitch-1.10.3
ldconfig
./bootstrap.sh -j
./configure --enable-portable-binary \
  --prefix=/usr --localstatedir=/var --sysconfdir=/etc \
  --with-gnu-ld --with-python --with-erlang --with-openssl \
  --enable-core-odbc-support --enable-zrtp
make
make install
# make cd-sounds-install
# make cd-moh-install

echo '编译安装g729编码'
cd /usr/local/src/mod_g729-master
# 配置g729编译环境
sed -i 's|FS_INCLUDES=/usr/local/freeswitch/include/freeswitch|FS_INCLUDES=/usr/local/src/freeswitch-1.10.3/src/include|g' Makefile
sed -i 's|FS_MODULES=/usr/local/freeswitch/|FS_MODULES=/usr/lib/freeswitch/|g' Makefile
sed -i '1a\LIBT_INCLUDES=/usr/local/src/freeswitch-1.10.3/libs/libteletone/src' Makefile
sed -i 's|-I$(FS_INCLUDES)|-I$(FS_INCLUDES) -I$(LIBT_INCLUDES)|' Makefile
ldconfig
make
make install
# 为freeswitch配置g729编码
sed -i 's|data="global_codec_prefs=OPUS,G722,PCMU,PCMA,H264,VP8"|data="global_codec_prefs=OPUS,G729,G722,PCMU,PCMA,H264,VP8"|' /etc/freeswitch/vars.xml
sed -i 's|data="outbound_codec_prefs=OPUS,G722,PCMU,PCMA,H264,VP8"|data="outbound_codec_prefs=OPUS,G729,G722,PCMU,PCMA,H264,VP8"|' /etc/freeswitch/vars.xml
sed -i '/outbound_codec_prefs=/a<X-PRE-PROCESS cmd="set" data="media_mix_inbound_outbound_codecs=true"/>' /etc/freeswitch/vars.xml

ldconfig

echo 'freeswitch主体已经设置安装完毕，开启了g729编码支持，并对H.264进行了汇编优化'
echo '配置目录在/etc/freeswitch'
echo '模块目录在/usr/lib/freeswitch/mod'
echo '资源目录在/usr/share/freeswitch'
echo '日志目录在/var/log/freeswitch'
echo 'freeswitch, fs_cli等工具已安装至/usr/bin目录，可以直接运行'
echo '下载附加音频文件请去/usr/local/src/freeswitch-1.10.3目录执行安装命令，如：make cd-sounds-install && make cd-moh-install'
