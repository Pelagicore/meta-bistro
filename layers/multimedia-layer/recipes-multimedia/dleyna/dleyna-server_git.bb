#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "dLeyna Server"
DEPENDS = "dleyna-core"
EXTRA_OECONF += " --enable-never-quit --enable-debug"
require dleyna-git.inc

inherit systemd
SRCREV = "b5394e2be1cfaefe1f442a7cacc1c3a1f0c1c1ff"
SRC_URI += "file://dleyna.service"

SYSTEMD_SERVICE_${PN} = "dleyna.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install_append() {
               install -Dm0644 ${WORKDIR}/dleyna.service ${D}/lib/systemd/system/dleyna.service
}

