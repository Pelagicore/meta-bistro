# meta-bistro

A Yocto/OpenEmbedded layer that provides in-vehicle infotainment (IVI) 
related functionality in an OpenEmbedded Linux system.

This layer aggregates original IVI related software recipes for software
components developed by Pelagicore. In addition, the layer contains
appendments to other open source Yocto/OpenEmbedded layer recipes
that are relevant for the aforementioned functionality.

Maintained at https://github.com/pelagicore/meta-bistro

## Dependencies

This layer currently depends on:

| meta layer             | git repo |
| ---------------------- |----------|
| poky                   | https://git.yoctoproject.org/git/poky |
| meta-openembedded      | http://cgit.openembedded.org/meta-openembedded |

### Optional dependencies

These are optional dependencies for the specific layer additions described
below:

| meta layer             | git repo |
| ---------------------- | -------- |
| meta-ivi               | https://github.com/GENIVI/meta-ivi |
| meta-qt5               | https://code.qt.io/yocto/meta-qt5.git |
| meta-virtualization    | https://git.yoctoproject.org/git/meta-virtualization |
| meta-openembedded/meta-filesystems | |
| meta-openembedded/meta-multimedia | |
| meta-openembedded/meta-networking | |
| meta-openembedded/meta-python | |

## Layers

Depending on the included layers used together with meta-bistro, additional
functionality is added from the `layers/*/` directories. This is done by adding
the `layers/*` directory if the name is in the `BBFILE_COLLECTIONS` variable,
as defined in the `conf/layer.conf` file.

The `BBFILE_COLLECTIONS` contains the names as given by each respective meta
layer, and hence the names of the directories can not be changed by us directly.

The general idea is that these layer additions should only be added to 
meta-bistro if the original layers are available in the current build system
setup.

## Branching

This repository will follow the Yocto release system. Whenever a new Yocto
release has been released, a new branch with the same name will be created
from the master branch.

All feature growth should happen first on the master branch, but will also be
cherry picked back to the latest Yocto release branch. Security and bug fixes
will be evaluated case by case and backported as necessary. The ambition is to
actively maintain the two latest releases and/or one year old releases in
this fashion.

## License and Copyright

Copyright &copy; 2015-2017 Pelagicore AB  
Copyright &copy; 2017-2019 Luxoft Sweden AB

All metadata is MIT licensed unless otherwise stated. Source code included
in tree for individual recipes is under the LICENSE stated in the associated
recipe (.bb file) unless otherwise stated.

License information for any other files is either explicitly stated
or defaults to MIT license.
