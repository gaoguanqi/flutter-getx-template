import '/common/base/base.dart';

import '${name}.dart';

class ${upName}Binding extends Bindings{

@override
void dependencies() {
        Get.lazyPut<${upName}Controller>(() => ${upName}Controller());
    }
 }