#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "dLeyna Server"
DEPENDS = "dleyna-core"
EXTRA_OECONF += " --enable-never-quit "
require dleyna-git.inc

inherit systemd
SRCREV = "fb4d75188711b02822c3a7dba0ed6d129f608542"
SRC_URI += " \ 
	   file://0001-Add-artist-and-AlbumArtURL-to-MediaContainer2.patch \
	   file://dleyna.service \
          "

SYSTEMD_SERVICE_${PN} = "dleyna.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install_append() {
               install -Dm0644 ${WORKDIR}/dleyna.service ${D}/lib/systemd/system/dleyna.service
}

