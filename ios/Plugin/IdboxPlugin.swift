import Foundation

@objc public class IdboxPlugin: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
     @objc public func registerRequest(_ value: String) -> String {
        print(value)
        return value
    }
}
