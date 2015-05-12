#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
# remove --enable-cxx as it procudes a build error
# This file can be deleted if someone finds a simpler/better way!
# ... or if it's fixed somewhere else.
# => do not move this file to a newer version until you've seen a libstdc++-build-error!
# => if you've see a bbappend warning and db is building -> delete this file and commit!

python () {
    extraconf = bb.data.getVar('EXTRA_OECONF', d, 1)
    extraconf = extraconf.replace("--enable-cxx", "")
    bb.data.setVar('EXTRA_OECONF', extraconf, d)
}
