import SwiftUI
import sharedCin

struct ContentView: View {
   @ObservedObject private(set) var viewModel: ViewModel
	let greet = Greeting().greet()
    

	var body: some View {
        Text(greet+viewModel.loadedData).multilineTextAlignment(.center)
	}
}


extension ContentView {
    @MainActor
     class ViewModel : ObservableObject {
        //let ktorData: String
        let ktor: KtorHelper
        @Published var loadedData = "nothing"
       
        init(ktor: KtorHelper){
           self.ktor = ktor
            self.loadDataFromKtor()
            //self.ktorData = "ojej"
        }
         
         func loadDataFromKtor() {
             ktor.ktorData(completionHandler: { loadedData, error in
                 if let loadedData = loadedData {
                     let date = Date(timeIntervalSince1970: (Double(loadedData)!))
                       let dateFormatter = DateFormatter()
                       dateFormatter.timeStyle = DateFormatter.Style.medium
                       dateFormatter.dateStyle = DateFormatter.Style.medium
                       dateFormatter.timeZone = .current
                 self.loadedData = dateFormatter.string(from: date)
             } else {
                 self.loadedData = "error"
             }
                 
             })
         }
         
     }
    
    
}
