import '/common/base/base.dart';
import '${name}.dart';

class ${upName}View extends BaseGetView<${upName}Controller> {
    ${upName}View({super.key});

    @override
    Widget build(BuildContext context) {
        return  Scaffold(
         body: _buildBody(),
        );
    }

    Widget _buildBody(){
        return const Center(
            child: Text('${name}'),
        );
    }
}