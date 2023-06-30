import SwiftUI
import sharedCin

struct ContentView: View {
   @ObservedObject private(set) var viewModel: ViewModel
	//let greet = Greeting().greet()
    

	var body: some View {
        //Text(greet+viewModel.loadedData).multilineTextAlignment(.center)
        VStack(alignment: HorizontalAlignment.leading) {
            Text("> arturlasok.com").font(.system(size: 30)).bold()
            //Spacer()
            HStack {
           
                VStack {
                    HStack {
                        Image(systemName: "placeholder image")
                            .data(url: URL(string: "http://server873539.nazwa.pl/static/1icon.png")!)
                            .frame(width: 32,height: 32)
                        
                        Text("AppName"+viewModel.loadedData.description).underline().bold().font(.system(size: 10)).multilineTextAlignment(.leading)
                        
                    }
                    Spacer()
                }.frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                
                VStack {
                    Image(systemName: "app image")
                        .data(url: URL(string: "http://server873539.nazwa.pl/static/1generator.png")!)
                        .frame(width: nil) .aspectRatio(contentMode: .fit)
                    Spacer()
                }.frame(minWidth: 0, maxWidth: .infinity)
                
            }.frame(minWidth: 0, maxWidth: .infinity)
            Spacer()
        }.padding(.horizontal,20)
    }
}

//viewmodel
extension ContentView {
    @MainActor
     class ViewModel : ObservableObject {
        //let ktorData: String
        let ktor: KtorHelper
        //@Published var loadedData = "nothing"
         @Published var loadedData : [KMMAppData] = []
       
        init(ktor: KtorHelper){
           self.ktor = ktor
            //self.loadDataFromKtor()
            self.loadAppsDataFromKtor()
            
        }
         func loadAppsDataFromKtor() {
             ktor.ktorAppsData(completionHandler: { loadedData, error in
                 if let loadedData = loadedData {
                     self.loadedData = loadedData } else { self.loadedData = [] }
                 
                 })
         }
         /*
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
         */
         
     }
    
    
}
extension Image {

func data(url:URL) -> Self {

if let data = try? Data(contentsOf: url) {

return Image(uiImage: UIImage(data: data)!)

.resizable()

}

return self

.resizable()

}

}
