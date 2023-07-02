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
            ForEach(viewModel.loadedData,id: \.self) { apka in
                HStack {
                    //app information
                    VStack(alignment: .leading) {
                   
                        HStack {
                            Image(systemName: "placeholder image")
                                .data(url: URL(string: apka.mIconLink)!)
                                .frame(width: 32,height: 32, alignment: .leading)
                            
                            Text(apka.mAppNamePL).underline().bold().font(.system(size: 8)).multilineTextAlignment(.leading)
                            
                        }
                        Text(apka.mAppDescPL).font(.system(size: 8)).multilineTextAlignment(.leading)                        //VStack {
                        //    JustifiedText(apka.mAppDescPL)
                        //}.frame(height: 40,alignment: .leading)
                        Text("Platform").bold().font(.system(size: 8)).multilineTextAlignment(.leading).padding(.top, 6)
                        Text(viewModel.loadPlatform(linkAnd: apka.mAppStoreLinkAndroid, linkIos: apka.mAppStoreLinkIOS, linkWeb: apka.mAppStoreLinkWeb)).font(.system(size: 8)).multilineTextAlignment(.leading).padding(.top, 1)
                        Text("Tech stack").bold().font(.system(size: 8)).multilineTextAlignment(.leading).padding(.top, 6)
                        Text(apka.mTechStack).font(.system(size: 8)).multilineTextAlignment(.leading).padding(.top, 1)
                        Spacer()
                    }.frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                    //app image
                    VStack {
                        Image(systemName: "app image")
                            .data(url: URL(string: apka.mPhotoLink)!)
                            .frame(width: nil) .aspectRatio(contentMode: .fit)
                        Spacer()
                    }.frame(minWidth: 0, maxWidth: .infinity)
                    
                }.frame(minWidth: 0, maxWidth: .infinity)
            }
            Spacer()
        }.padding(.horizontal,10)
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
         func loadPlatform(linkAnd: String, linkIos: String, linkWeb:String) -> String {
             ktor.platformForIos(linkAnd: linkAnd, linkIos: linkIos, linkWeb: linkWeb)
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
struct JustifiedText: UIViewRepresentable {
  private let text: String
  private let font: UIFont

  init(_ text: String, font: UIFont = .systemFont(ofSize: 8)) {
    self.text = text
    self.font = font
  }

  func makeUIView(context: Context) -> UITextView {
    let textView = UITextView()
    textView.font = font
    textView.textAlignment = .justified
    return textView
  }

  func updateUIView(_ uiView: UITextView, context: Context) {
    uiView.text = text
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
