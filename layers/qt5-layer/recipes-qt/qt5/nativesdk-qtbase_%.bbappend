#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://qmake-sdk "

QMAKESDK = "${D}${OE_QMAKE_PATH_HOST_BINS}/qmake-sdk"

#We should use TARGET_VENDOR here but this is overridden to SDK_VENDOR
REAL_MULTIMACH_TARGET_SYS := "${TUNE_PKGARCH}-${DISTRO}-linux${LIBCEXTENSION}${ABIEXTENSION}"

do_install_append() {
    sed -i.bak "s#%%%REAL_MULTIMACH_TARGET_SYS%%%#${REAL_MULTIMACH_TARGET_SYS}#g" ${S}/../qmake-sdk
    install -D -m 777 ${S}/../qmake-sdk ${QMAKESDK}
}
