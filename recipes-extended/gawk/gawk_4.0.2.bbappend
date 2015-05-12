#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
# Hack to satisfy vim rpm package desire for /usr/bin/nawk

do_install_append() {
       install -d ${D}${bindir}
       ln -s gawk ${D}${bindir}/nawk
}

FILES_${PN} += "${bindir}/nawk"
