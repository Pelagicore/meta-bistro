# Copyright (C) 2015 Pelagicore AB
DESCRIPTION = "dLeyna Renderer"
DEPENDS = "dleyna-core"
require dleyna-git.inc
SRCREV = "b7514080bbcd88724c8609a56c46ed97c1ae66e4"

EXTRA_OECONF += " --enable-lib-only "
