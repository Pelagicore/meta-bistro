#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
require recipes-devtools/vala/${BPN}.inc

PR = "${INC_PR}.1"

#SRC_URI += " file://0001-git-version-gen-don-t-append-dirty-if-we-re-not-in-g.patch"

SRC_URI[md5sum] = "5055d3f8f897ad1a77fd38887c85a92c"
SRC_URI[sha256sum] = "92c61b94a427f281ba2537b69135a3be23248b153268057d7195003dd6aba28c"

# Don't use as default until further tested
DEFAULT_PREFERENCE = "-1"
