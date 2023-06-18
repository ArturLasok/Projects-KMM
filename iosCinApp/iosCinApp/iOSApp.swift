import SwiftUI
import sharedCin

@main
struct iOSApp: App {

   init() {
       KoinHelperKt.doInitKoin()
   }
    let kh = KtorHelper()
	var body: some Scene {
		WindowGroup {
            ContentView(viewModel: .init(ktor: kh))
		}
	}
}
