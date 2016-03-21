package assignmentTwo

import java.util.concurrent.ConcurrentHashMap

import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ReadExcel {

	def "Reading An Excel File"(){
		
		def file = new File('state.xlsx')
		def fis = new FileInputStream(file)
		def workbook = new XSSFWorkbook(fis)
		def sheet = workbook.getSheetAt(0)
		
		def num = sheet.lastRowNum
		def CityStateMap = new ConcurrentHashMap<String, String>()
		def StateCountMap = new ConcurrentHashMap<String, Integer>()
		
			for (def i=1;i<=num;i++){
				CityStateMap.put(sheet.getRow(i).getCell(1), sheet.getRow(i).getCell(0))
			}
			
			for (String value : CityStateMap.values()){
				if(StateCountMap.size()==0){
					StateCountMap.put(value, 1)
				}
				else if(StateCountMap.size()!=0){
					def flag =false
					for (String State : StateCountMap.keySet() ){
						if (State == value){
							def count = StateCountMap.get(State)
							StateCountMap.remove(State)
							StateCountMap.put(State, count+1)
							flag=true
						}
					}
					if(flag==false){
						StateCountMap.put(value, 1)
					}
				}
			}
			then:
			println "Number of Cities In Each State:" +StateCountMap
			
			def arr = new ArrayList<String>()
			for (Object o : StateCountMap.entrySet()) {
				
				if(o.getValue()>2){
								
					arr.add(o.getKey())
				}
			}
			println "States having more than 2 Cities:" + arr
		}
}
