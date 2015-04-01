package edu.harvard.capstone.parser



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


class EquipmentController {

    def springSecurityService
    def parserService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Equipment.list(params), model:[equipmentInstanceCount: Equipment.count()]
    }

    def show(Equipment equipmentInstance) {
        render(contentType: "application/json") {
            [equipment: equipmentInstance]
        }
    }

    def create() {
        respond new Equipment(params)
    }

    
    def save() {

        if (!springSecurityService.isLoggedIn()){
            render(contentType: "application/json") {
                [error: "User not logged in"]
            }
            return
        } 
        
        def data = request.JSON        
        
        if (!data) {
            render(contentType: "application/json") {
                [error: "No data received"]
            }
            return
        }

        def equipmentInstance = parserService.newEquipment(data.name, data.machineName, data.description, data.toString())

        if(equipmentInstance == null){
            render(contentType: "application/json") {
                [error: "Error creating the equipment"]
            }
            return
        }

        if(equipmentInstance.hasErrors()){
            render(contentType: "application/json") {
                [error: equipmentInstance.errors]
            }
            return            
        }

        render(contentType: "application/json") {
            [equipment: equipmentInstance]
        }
    }

    def edit(Equipment equipmentInstance) {
        respond equipmentInstance
    }


    def update(Equipment equipmentInstance) {
        if (equipmentInstance == null) {
            notFound()
            return
        }

        if (equipmentInstance.hasErrors()) {
            respond equipmentInstance.errors, view:'edit'
            return
        }

        equipmentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Equipment.label', default: 'Equipment'), equipmentInstance.id])
                redirect equipmentInstance
            }
            '*'{ respond equipmentInstance, [status: OK] }
        }
    }


    def delete(Equipment equipmentInstance) {

        if (equipmentInstance == null) {
            notFound()
            return
        }

        equipmentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Equipment.label', default: 'Equipment'), equipmentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipment.label', default: 'Equipment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
